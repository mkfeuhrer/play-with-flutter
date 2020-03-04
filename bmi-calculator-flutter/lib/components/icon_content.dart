import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/icon_data.dart';
import '../constants.dart';

class IconContent extends StatelessWidget {
  final IconDataSolid icon;
  final String text;

  IconContent({@required this.icon, @required this.text});

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Icon(
          icon,
          size: 80.0,
        ),
        SizedBox(
          height: 15.0,
        ),
        Text(
          text,
          style: kLabelTextStyle,
        ),
      ],
    );
  }
}
