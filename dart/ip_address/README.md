# get_ip_address

A flutter package to get the system ip address

## Getting Started

Add the dependency in your `pubspec.yaml` file

`ip_address: <version>`

Then run,

`flutter pub get` or `pub get`.

## How to use?

Add

`var ipAddress = IpAddress(type: RequestType.json);`

`type` is optional, default value is `RequestType.text`.

Now, to get the IpAddress

`dynamic data = await ipAddress.getIpAddress();`

So, depending on `type` the ipAddress will be given.

If any error occurred it will throw `IpAddressException`.