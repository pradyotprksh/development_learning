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
  }

  final NewsService _newsService;

  void _getNews(GetNews event, Emitter<HomeState> emit) async {
    emit(state.copyWith(pageState: PageState.loading));
    await _newsService.getNews(1).then(
      (value) {
        emit(
          state.copyWith(
            pageState: PageState.success,
            newsData: value.data,
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
}
