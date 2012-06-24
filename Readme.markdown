meta-kipr
=========

This repository is meant to be used in conjunction with the instructions found here: http://www.kosagi.com/w/index.php?title=Kovan_firmware

Before running ./oebb.sh update, append the following line to oe/sources/layers.txt: `meta-kipr,git://github.com/kipr/meta-kipr,master,HEAD`
	
Follow the remaining steps, but replace: `bitbake kovan-bringup-image`

With: `bitbake kovan-distribution-image`