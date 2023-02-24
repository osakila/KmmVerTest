import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_kmm_ver_test/flutter_kmm_ver_test.dart';
import 'package:flutter_kmm_ver_test/flutter_kmm_ver_test_platform_interface.dart';
import 'package:flutter_kmm_ver_test/flutter_kmm_ver_test_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterKmmVerTestPlatform
    with MockPlatformInterfaceMixin
    implements FlutterKmmVerTestPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FlutterKmmVerTestPlatform initialPlatform = FlutterKmmVerTestPlatform.instance;

  test('$MethodChannelFlutterKmmVerTest is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterKmmVerTest>());
  });

  test('getPlatformVersion', () async {
    FlutterKmmVerTest flutterKmmVerTestPlugin = FlutterKmmVerTest();
    MockFlutterKmmVerTestPlatform fakePlatform = MockFlutterKmmVerTestPlatform();
    FlutterKmmVerTestPlatform.instance = fakePlatform;

    expect(await flutterKmmVerTestPlugin.getPlatformVersion(), '42');
  });
}
