import 'package:fews/app/app.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class HomeBloc extends Bloc<HomeEvent, HomeState> {
  HomeBloc()
      : super(
          const HomeState(),
        ) {
    on<GetNews>(_getNews);
  }

  void _getNews(GetNews event, Emitter<HomeState> emit) {
    emit(state.copyWith(pageState: PageState.loading));
  }
}
