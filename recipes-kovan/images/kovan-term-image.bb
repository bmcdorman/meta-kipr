# Image for assisting in hardware bringup

require systemd-image.bb

	
+# Replace connman with networkmanager
+CONMANPKGS = "networkmanager"


IMAGE_INSTALL += " \
	usbutils \
	i2c-tools \
	task-core-sdk \
	alsa-utils \
	devmem2 \
	iw \
	bonnie++ \
	hdparm \
	iozone3 \
	iperf \
	lmbench \
	rt-tests \
	evtest \
	systemd-analyze \
	strace gdb lsof bc bash procps kexec \
	gadget-init \
	openssh-scp openssh-ssh openssh-scp \
	kovan-util \
	udev-extra-rules \
	python-fcntl python-subprocess python-ctypes python-dbus python-terminal \
	gcc \
	g++ \
	${XSERVER} \
	kernel-module-cs53l32a \
	kernel-module-cx231xx-alsa \
	kernel-module-cx231xx \
	kernel-module-cx2341x \
	kernel-module-cx25840 \
	kernel-module-em28xx-alsa \
	kernel-module-em28xx \
	kernel-module-et61x251 \
	kernel-module-hdpvr \
	kernel-module-konicawc \
	kernel-module-msp3400 \
	kernel-module-mt9v011 \
	kernel-module-pvrusb2 \
	kernel-module-pwc \
	kernel-module-s2255drv \
	kernel-module-saa7115 \
	kernel-module-se401 \
	kernel-module-stv680 \
	kernel-module-tuner \
	kernel-module-tveeprom \
	kernel-module-tvp5150 \
	kernel-module-w9968cf \
	kernel-module-wm8775 \
	kernel-module-zc0301 \
	kernel-module-zr364xx \
	kernel-module-usbtest \
	kernel-module-g-zero \
	cmake \
	xinput-calibrator \
"
export IMAGE_BASENAME = "term"

