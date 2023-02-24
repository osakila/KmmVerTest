import Flutter
import UIKit
import KmmVerTest

public class SwiftFlutterKmmVerTestPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_kmm_ver_test", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterKmmVerTestPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result(PlatformKt.getPlatform().name)
  }
}
