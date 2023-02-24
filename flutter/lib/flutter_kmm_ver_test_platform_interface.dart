import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_kmm_ver_test_method_channel.dart';

abstract class FlutterKmmVerTestPlatform extends PlatformInterface {
  /// Constructs a FlutterKmmVerTestPlatform.
  FlutterKmmVerTestPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterKmmVerTestPlatform _instance = MethodChannelFlutterKmmVerTest();

  /// The default instance of [FlutterKmmVerTestPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterKmmVerTest].
  static FlutterKmmVerTestPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterKmmVerTestPlatform] when
  /// they register themselves.
  static set instance(FlutterKmmVerTestPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
