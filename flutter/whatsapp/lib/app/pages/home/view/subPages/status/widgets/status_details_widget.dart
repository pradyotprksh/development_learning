import 'package:auto_size_text/auto_size_text.dart';
import 'package:flutter/material.dart';
import 'package:get/get_rx/get_rx.dart';
import 'package:get/get_state_manager/get_state_manager.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class StatusDetailsWidget extends StatefulWidget {
  const StatusDetailsWidget({
    required this.statusDetails,
    required this.isSeen,
    this.currentStatusNumber = 0,
    super.key,
  });

  final UserWithSingleStatusDetails statusDetails;
  final int currentStatusNumber;
  final void Function(String, RxList<StatusSeenDetails>) isSeen;

  @override
  State<StatusDetailsWidget> createState() => _StatusDetailsWidgetState();
}

class _StatusDetailsWidgetState extends State<StatusDetailsWidget> {
  late StatusDetails currentStatus;
  late int currentStatusIndex;

  @override
  void initState() {
    super.initState();
    currentStatusIndex = widget.currentStatusNumber;
    currentStatus = widget
        .statusDetails.statusWithSeenDetails[currentStatusIndex].statusDetails;
  }

  @override
  Widget build(BuildContext context) {
    widget.isSeen(
      currentStatus.statusId,
      widget
          .statusDetails.statusWithSeenDetails[currentStatusIndex].statusSeenBy,
    );

    return Scaffold(
      extendBodyBehindAppBar: true,
      extendBody: true,
      backgroundColor: Color(currentStatus.color),
      appBar: AppBar(
        automaticallyImplyLeading: false,
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
      body: Stack(
        children: [
          if (currentStatus.filePathUrl == null)
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                const Spacer(),
                Container(
                  padding: ThemeEdgeInsets.all20,
                  width: double.infinity,
                  child: AutoSizeText(
                    currentStatus.status,
                    style: GoogleFonts.getFont(
                      currentStatus.fontFamily,
                    ).copyWith(
                      fontSize: 25,
                    ),
                    textAlign: TextAlign.center,
                    minFontSize: 10,
                    overflow: TextOverflow.ellipsis,
                    maxLines: 10,
                  ),
                ),
                const Spacer(),
              ],
            ),
          if (currentStatus.filePathUrl != null)
            if (currentStatus.isFileImage == true)
              CachedNetworkImageWidget(
                imageUrl: currentStatus.filePathUrl ?? '',
                placeholder: ThemeSizedBox.shrink,
                width: double.infinity,
                height: double.infinity,
              ),
          Column(
            children: [
              const Spacer(),
              VideoWidget(
                path: currentStatus.filePathUrl,
                isNetwork: true,
                play: currentStatus.isFileImage == false,
                showWidget: currentStatus.filePathUrl != null &&
                    currentStatus.isFileImage == false,
              ),
              const Spacer(),
            ],
          ),
          if (currentStatus.filePathUrl != null)
            if (currentStatus.status.isNotEmpty)
              Align(
                alignment: Alignment.bottomCenter,
                child: Container(
                  color: Colors.black38,
                  width: double.infinity,
                  child: Padding(
                    padding: ThemeEdgeInsets.all15,
                    child: Text(
                      currentStatus.status,
                      textAlign: TextAlign.center,
                      maxLines: 2,
                    ),
                  ),
                ),
              ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              GestureDetector(
                onTap: () {
                  if (currentStatusIndex != 0) {
                    setState(
                      () {
                        final currentStatusDetails = widget.statusDetails
                            .statusWithSeenDetails[--currentStatusIndex];
                        currentStatus = currentStatusDetails.statusDetails;
                      },
                    );
                  }
                },
                child: Container(
                  height: double.infinity,
                  width: context.mediaQuery.size.width / 3,
                  color: Colors.transparent,
                ),
              ),
              GestureDetector(
                onTap: () {
                  if (currentStatusIndex <
                      widget.statusDetails.statusWithSeenDetails.length - 1) {
                    setState(() {
                      final currentStatusDetails = widget.statusDetails
                          .statusWithSeenDetails[++currentStatusIndex];
                      currentStatus = currentStatusDetails.statusDetails;
                    });
                  }
                },
                child: Container(
                  width: context.mediaQuery.size.width / 3,
                  height: double.infinity,
                  color: Colors.transparent,
                ),
              ),
            ],
          ),
          Column(
            children: [
              ThemeSizedBox.height30,
              Row(
                mainAxisSize: MainAxisSize.min,
                children: [
                  IconButton(
                    onPressed: () {
                      context.navigator.pop();
                    },
                    icon: const Icon(
                      Icons.arrow_back,
                    ),
                  ),
                  Obx(
                    () {
                      final userDetails =
                          widget.statusDetails.userDetails.value;
                      if (userDetails != null) {
                        return Flexible(
                          child: ListTile(
                            leading: CachedNetworkImageWidget(
                              imageUrl: userDetails.profileImage ?? '',
                              placeholder: CircleAvatar(
                                radius: 20,
                                backgroundColor: context.themeData.primaryColor,
                                backgroundImage: const AssetImage(
                                  AssetsPath.defaultAvatar,
                                ),
                              ),
                              height: 40,
                              width: 40,
                              clipToCircle: true,
                            ),
                            title: Text(
                              userDetails.name ?? '',
                            ),
                            subtitle: Text(
                              AppUtilsMethods.timeAgo(
                                currentStatus.createdOnTimeStamp,
                                context,
                              ),
                            ),
                          ),
                        );
                      } else {
                        return ThemeSizedBox.shrink;
                      }
                    },
                  ),
                ],
              ),
            ],
          ),
        ],
      ),
    );
  }
}
