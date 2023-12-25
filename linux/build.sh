#!/usr/bin/bash
echo " ------ Brick Breaker Installer ------"
sleep 1
echo "Installing......"
jar_dir="/data/IdeaProjects/BrickerBreaker/out/artifacts/BrickerBreaker_jar/"
if [ ! -d "$HOME/BrickBreaker" ]
then
  mkdir ~/BrickBreaker
fi
cp -r $jar_dir ~/BrickBreaker
cp BrickBreaker.desktop  ~/BrickBreaker/
cp Logo.svg ~/BrickBreaker/
cd ~/BrickBreaker
cp BrickBreaker.desktop ~/.local/share/applications/
sleep 1
echo "Do you want to add a desktop shortcut [y/n] ?"
read ch
if [[ "$ch" == "y" ]]
then
  cp BrickBreaker.desktop ~/Desktop/
fi
echo "Brick Breaker is Installed ! Enjoy the game."
