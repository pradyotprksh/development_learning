import 'package:stackexchange_api/stackexchange_api.dart';

void main() async {
  final stackExchange = StackExchangeApi(
    credentials: Credentials(
      clientId: 123456,
    ),
  );

  final allErrors = await stackExchange.getAllErrors();
  injectLogger('stackexchangeApi.example').info(allErrors.items.length);

  final sites = await stackExchange.getSites(pageSize: -1);
  injectLogger('stackexchangeApi.example').info(sites.items.length);
}
