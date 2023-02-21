import 'package:flutter/material.dart';
import 'package:qr_flutter/qr_flutter.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class QrCodeGeneratorView extends StatelessWidget {
  const QrCodeGeneratorView({super.key});

  @override
  Widget build(BuildContext context) {
    final arguments = context.routeSettings?.arguments as Map<String, dynamic>;
    final qrData = arguments[Keys.qrCodeData] as String? ?? '';
    final imageUrl = arguments[Keys.imageUrl] as String? ?? '';
    final userDetails = arguments[Keys.userDetails] as UserDetails?;

    return Scaffold(
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      appBar: AppBar(),
      body: Column(
        children: [
          const Spacer(),
          SizedBox(
            width: context.mediaQuery.size.width * 0.65,
            height: context.mediaQuery.size.height * 0.46,
            child: Stack(
              alignment: Alignment.topCenter,
              children: [
                Align(
                  alignment: Alignment.center,
                  child: Container(
                    width: context.mediaQuery.size.width * 0.65,
                    height: context.mediaQuery.size.height * 0.40,
                    alignment: Alignment.center,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(
                        40,
                      ),
                      border: Border.all(
                        color: context.themeData.primaryColor,
                      ),
                      color: Colors.white,
                    ),
                    child: QrImage(
                      data: qrData,
                      padding: ThemeEdgeInsets.all40,
                    ),
                  ),
                ),
                if (userDetails != null)
                  UserImageWidget(
                    profileImage: userDetails.profileImage ?? '',
                    userId: userDetails.userId,
                    isOnline: null,
                    currentMood: null,
                    size: 60,
                    enableAction: false,
                    useAvatarAsProfile: userDetails.useAvatarAsProfile,
                    avatarDetails: userDetails.avatarDetails,
                  ),
                if (imageUrl.isNotEmpty && userDetails == null)
                  CachedNetworkImageWidget(
                    imageUrl: imageUrl,
                    placeholder: CircleAvatar(
                      radius: 30,
                      backgroundColor: context.themeData.primaryColor,
                      child: const Icon(
                        Icons.qr_code,
                        color: Colors.white,
                      ),
                    ),
                    height: 60,
                    width: 60,
                    clipToCircle: true,
                  ),
              ],
            ),
          ),
          const Spacer(),
          Container(
            padding: ThemeEdgeInsets.all15,
            width: double.infinity,
            child: ElevatedButton(
              onPressed: () async {
                final navigator = context.navigator;
                final data = await navigator.pushNamed(
                  Routes.qrCodeScanner,
                ) as String?;
                _getResultDetails(navigator, data);
              },
              child: Text(
                context.translator.scanQrCode,
              ),
            ),
          ),
        ],
      ),
    );
  }

  void _getResultDetails(NavigatorState navigator, String? data) {
    final result = ScanCodeExtractor.getScanCodeDetails(data);
    switch (result.runtimeType) {
      case UserScanResult:
        final details = result as UserScanResult;
        navigator.pushNamed(
          Routes.messages,
          arguments: <String, String>{
            Keys.userId: details.userId,
          },
        );
        break;
      case NoneResult:
        break;
    }
  }
}
