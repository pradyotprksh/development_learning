import 'package:dio/dio.dart';
import 'package:fews/app/app.dart';
import 'package:fews/domain/domain.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class HomeBloc extends Bloc<HomeEvent, HomeState> {
  HomeBloc(
    this._newsService,
  ) : super(
          const HomeState(),
        ) {
    on<GetNews>(_getNews);
    on<UpdatePage>(_updatePage);
  }

  final NewsService _newsService;

  void _getNews(GetNews event, Emitter<HomeState> emit) async {
    if (event.pageNumber == 1) {
      emit(
        state.copyWith(
          pageState: PageState.loading,
          newsData: null,
        ),
      );
    } else {
      emit(
        state.copyWith(
          pageState: PageState.loading,
        ),
      );
    }
    await _newsService
        .getNews(
      event.pageNumber,
      event.language,
    )
        .then(
      (value) {
        var news = value.data;
        if (event.pageNumber != 1) {
          news = [
            ...state.newsData,
            ...value.data,
          ];
        }

        emit(
          state.copyWith(
            pageState: PageState.success,
            newsData: news,
            pageNumber: event.pageNumber,
          ),
        );
      },
    ).catchError(
      (Object error) {
        switch (error.runtimeType) {
          case DioException:
            final res = (error as DioException).response;
            emit(
              state.copyWith(
                pageState: PageState.error,
                errorMessage: res?.statusMessage,
              ),
            );
          default:
            emit(
              state.copyWith(
                pageState: PageState.error,
              ),
            );
        }
      },
    );
  }

  void _updatePage(
    UpdatePage event,
    Emitter<HomeState> emit,
  ) {
    add(
      GetNews(
        pageNumber: state.pageNumber + 1,
      ),
    );
  }
}
