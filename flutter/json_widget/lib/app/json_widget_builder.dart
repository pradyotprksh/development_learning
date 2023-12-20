import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:json_widget/app/app.dart';
import 'package:json_widget/core/core.dart';

class JsonWidgetBuilder extends StatefulWidget {
  const JsonWidgetBuilder({super.key, required this.jsonString, this.options});

  final String jsonString;
  final Options? options;

  @override
  State<JsonWidgetBuilder> createState() => _JsonWidgetBuilderState();
}

class _JsonWidgetBuilderState extends State<JsonWidgetBuilder> {
  @override
  void initState() {
    Bloc.observer = JsonWidgetBlocObserver(
      showLogs: widget.options?.showLogs ?? true,
    );
    super.initState();
  }

  @override
  Widget build(BuildContext context) => MultiBlocProvider(
        providers: [
          BlocProvider(
            create: (_) => JsonWidgetBuilderBloc()
              ..add(
                OnJsonReceivedEvent(
                  jsonString: widget.jsonString,
                ),
              ),
          )
        ],
        child: BlocConsumer<JsonWidgetBuilderBloc, JsonWidgetBuilderState>(
          listener: (_, state) {},
          builder: (_, state) {
            if (state is JsonInvalidState) {
              return JsonErrorWidget(
                errorWidget: widget.options?.onAnyErrorWidget,
              );
            }

            return const Placeholder();
          },
        ),
      );
}
