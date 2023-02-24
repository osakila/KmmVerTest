import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { multiply, getPlatformName } from 'rn-kmm-ver-test';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();
  const [result2, setResult2] = React.useState<string | undefined>();

  React.useEffect(() => {
    multiply(3, 7).then(setResult);
  }, []);

  React.useEffect(() => {
    getPlatformName().then(setResult2);
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result2}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
