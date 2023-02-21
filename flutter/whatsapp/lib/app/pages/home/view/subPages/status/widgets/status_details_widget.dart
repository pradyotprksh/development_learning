import 'package:auto_size_text/auto_size_text.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class StatusDetailsWidget extends StatefulWidget {
  const StatusDetailsWidget({
    required this.statusDetails,
    this.userDetails,
    required this.isSeen,
    this.currentStatusNumber = 0,
    this.isCurrentUser = false,
    super.key,
  });

  final UserWithSingleStatusDetails statusDetails;
  final UserDetails? userDetails;
  final int currentStatusNumber;
  final bool isCurrentUser;
  final void Function(String) isSeen;

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
    currentStatus = widget.statusDetails.statusDetails[currentStatusIndex];
  }

  @override
  Widget build(BuildContext context) {
    widget.isSeen(currentStatus.statusId);

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
                        currentStatus = widget
                            .statusDetails.statusDetails[--currentStatusIndex];
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
                      widget.statusDetails.statusDetails.length - 1) {
                    setState(() {
                      currentStatus = widget
                          .statusDetails.statusDetails[++currentStatusIndex];
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
                  Flexible(
                    child: ListTile(
                      contentPadding: ThemeEdgeInsets.zero,
                      leading: UserImageWidget(
                        profileImage: widget.userDetails?.profileImage ?? '',
                        userId: widget.userDetails?.userId ?? '',
                        currentMood: widget.userDetails?.currentMood,
                        isOnline: widget.userDetails?.isOnline,
                        extraAction: () {
                          context.navigator.pop();
                        },
                        enableAction: !widget.isCurrentUser,
                        useAvatarAsProfile:
                            widget.userDetails?.useAvatarAsProfile ?? false,
                        avatarDetails: widget.userDetails?.avatarDetails,
                      ),
                      title: Text(
                        widget.userDetails?.name ?? '',
                      ),
                    ),
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
