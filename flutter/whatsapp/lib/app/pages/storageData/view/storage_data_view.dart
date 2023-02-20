import 'package:filesize/filesize.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class StorageDataView extends StatelessWidget {
  const StorageDataView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.dataAndStorage,
          ),
        ),
        body: BlocBuilder<NetworkBloc, NetworkState>(
          builder: (_, networkState) {
            UtilsLogger.errorLog(
                "${filesize(networkState.totalDownloadFileSize)} ${filesize(networkState.totalUploadFileSize)}");
            return Container();
          },
        ),
      );
}
