# AttenCalc

Atten Calc is a simple attenuation calculator for computing fiber link attenuation and light budgets on fiber circuits based on user input. It makes these calculations for both 1310nm and 1550nm waves. It is both simple and customizable to meet the needs of the user as they use different equipment and tools that may create different losses than the default settings. 

## Getting Started



### Prerequisites

All you will need is an Android device running Lollipop (Android 5.0) or higher.

### Installing

The application can be installed by downloading it directly from the Google Play Store or by installing the .apk file. If using the .apk file you may need to enable app installation from unknown sources. The Android OS should take care of the rest.

## Using the Software

Once installed, Atten Calc can be accessed through the app drawer. On the initial run, all loss parameters are set to default as follows.

```
Under 1310: 
Passive Loss = 2.9db
Connector Loss = .75db
Splice Loss = .1db
Link Loss(per km) = .35db
```
```
Under 1550: 
Passive Loss = 2.7db
Connector Loss = .75db
Splice Loss = .1db
Link Loss(per km) = .22db
```
### Performing Calculations

Starting Light level and Link Distance are both manually entered by the user. Splice, Passive, and Connector count can be adjusted by using each up and down error respectively. The totals for each will be shown between each set of up and down arrows.

Pressing the Calculate button displays the calculations based on the user input. The Clear button will clear all calculations and input ready for the next task.

### Changing Default Values

Pressing the Options button takes the user to an options screen that displays all the current loss parameters. Here the user can customize each parameter to their need. by editting it in the test box and pressing the Commit Changes button. This will commit the changes and return the user to the main screen.

Pressing the Restore Defaults button will place the default values back into the text boxes where the user can commit then using the Commit Changes button.

## Built With

* Android Studio 3.6.3
* GIMP 2.8 GNU Image Manipulation Program

## Authors

* **Josh Shoaf** - *sole contributor*
