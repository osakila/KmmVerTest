import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'rn-kmm-ver-test' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const RnKmmVerTest = NativeModules.RnKmmVerTest
  ? NativeModules.RnKmmVerTest
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function multiply(a: number, b: number): Promise<number> {
  return RnKmmVerTest.multiply(a, b);
}

export function getPlatformName(): Promise<string> {
  return RnKmmVerTest.getPlatformName();
}
