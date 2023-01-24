import 'package:auto_size_text/auto_size_text.dart';
import 'package:flutter/material.dart';
import 'package:get/get_state_manager/get_state_manager.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class StatusDetailsWidget extends StatefulWidget {
  const StatusDetailsWidget({
    required this.statusDetails,
    this.currentStatusNumber = 0,
    super.key,
  });

  final UserWithSingleStatusDetails statusDetails;
  final int currentStatusNumber;

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
  Widget build(BuildContext context) => Scaffold(
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
            if (currentStatus.filePathUrl != null)
              if (currentStatus.isFileImage == false)
                Column(
                  children: [
                    const Spacer(),
                    VideoWidget(
                      path: currentStatus.filePathUrl ?? '',
                      isNetwork: true,
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
              children: [
                GestureDetector(
                  onTap: () {
                    if (currentStatusIndex != 0) {
                      setState(() {
                        currentStatus = widget
                            .statusDetails.statusDetails[--currentStatusIndex];
                      });
                    }
                  },
                  child: Container(
                    height: double.infinity,
                    width: context.mediaQuery.size.width / 2,
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
                    width: context.mediaQuery.size.width / 2,
                    height: double.infinity,
                    color: Colors.transparent,
                  ),
                ),
              ],
            ),
            Column(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                ThemeSizedBox.height30,
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
                    final userDetails = widget.statusDetails.userDetails.value;
                    if (userDetails != null) {
                      return ListTile(
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
      );
}
