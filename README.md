Macro Recorder
=============
------------------------------------------
Latest Version: [v.1.0.0][releases]

A simple and lightweight global mouse and keyboard recorder and player. The recordings log the time between actions to simulate realistic playback when playing a recording. 

Mouse clicks and keystrokes are recorded as presses and releases, allowing simulation of dragging the mouse or holding down a key. This allows for commands that involve a combination of keys.
<br></br>

Library
----
[JNativeHook][home] ([v1.2.0-Beta2][git]) distributed under the GNU GPL
<br></br>

Requirements
----
*Only tested on 64-Bit Windows 7 with Lenono T430s keyboard. Should work with most North American keyboards.*

- JDK 1.6 or higher
- Windows 7
<br></br>

Use
----
Download latest [release][releases]. Open cmd and go to directory containing the .jar file. Type java -jar [releaseName].jar

For recording, type in the text field for desired output filename. Files are saved in the same directory as the .jar. Press record when ready.

Player and recording can be interrupted at any time by pressing and releasing ESC.

[home]:https://code.google.com/p/jnativehook/
[git]:https://github.com/kwhat/jnativehook/releases
[releases]:https://github.com/georgeke/macroRecorder/releases
    
