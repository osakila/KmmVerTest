import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_kmm_ver_test_platform_interface.dart';

/// An implementation of [FlutterKmmVerTestPlatform] that uses method channels.
class MethodChannelFlutterKmmVerTest extends FlutterKmmVerTestPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_kmm_ver_test');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
