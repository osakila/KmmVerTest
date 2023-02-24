
import 'flutter_kmm_ver_test_platform_interface.dart';

class FlutterKmmVerTest {
  Future<String?> getPlatformVersion() {
    return FlutterKmmVerTestPlatform.instance.getPlatformVersion();
  }
}
