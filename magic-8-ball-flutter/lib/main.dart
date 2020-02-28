import 'package:flutter/material.dart';
import 'dart:math';

void main() => runApp(Ball());

class Ball extends StatefulWidget {
  @override
  _BallState createState() => _BallState();
}

class _BallState extends State<Ball> {
  int selectedBallNum = 1;

  UpdateSelectedBallNum() {
    setState(() {
      selectedBallNum = Random().nextInt(5) + 1;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: MaterialApp(
        home: Scaffold(
          body: Container(
            child: Center(
              child: FlatButton(
                onPressed: () => UpdateSelectedBallNum(),
                child: Image.asset('images/ball$selectedBallNum.png'),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
