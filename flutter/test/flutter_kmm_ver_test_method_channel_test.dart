import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_kmm_ver_test/flutter_kmm_ver_test_method_channel.dart';

void main() {
  MethodChannelFlutterKmmVerTest platform = MethodChannelFlutterKmmVerTest();
  const MethodChannel channel = MethodChannel('flutter_kmm_ver_test');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
