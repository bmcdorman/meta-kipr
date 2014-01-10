# Image for assisting in hardware bringup

require systemd-image.bb

CONMANPKGS = "networkmanager"

IMAGE_INSTALL += " \
	autoconf \
	automake \
	binutils \
	binutils-symlinks \
	coreutils \
	cpp \
	cpp-symlinks \
	ccache \
	diffutils \
	gcc \
	gcc-symlinks \
	g++ \
	g++-symlinks \
	gettext \
	make \
	intltool \
	libstdc++ \
	libstdc++-dev \
	libtool \
	perl-module-re \
	perl-module-text-wrap \
	pkgconfig \
	findutils \
	quilt \
	less \
	ldd \
	file \
	tcl \
	\
	usbutils \
	i2c-tools \
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
	openssh openssh-scp openssh-ssh \
	kovan-fpga \
	kovan-kmod \
	kovan-serial \
	udev udev-kovan-rules \
	python-fcntl python-subprocess python-ctypes python-terminal \
        \
	${XSERVER} \
	botui \
	libkovan \
	libkovan-dev \
	opencv-dev \
	xinput-calibrator \
	kernel-module-cs53l32a \
	kernel-module-cx231xx-alsa \
	kernel-module-cx231xx \
	kernel-module-cx2341x \
	kernel-module-cx25840 \
	kernel-module-em28xx-alsa \
	kernel-module-em28xx \
	kernel-module-et61x251 \
	kernel-module-gspca-benq \
	kernel-module-gspca-conex \
	kernel-module-gspca-cpia1 \
	kernel-module-gspca-etoms \
	kernel-module-gspca-finepix \
	kernel-module-gspca-gl860 \
	kernel-module-gspca-jeilinj \
	kernel-module-gspca-m5602 \
	kernel-module-gspca-main \
	kernel-module-gspca-mars \
	kernel-module-gspca-mr97310a \
	kernel-module-gspca-ov519 \
	kernel-module-gspca-ov534 \
	kernel-module-gspca-ov534-9 \
	kernel-module-gspca-pac207 \
	kernel-module-gspca-pac7302 \
	kernel-module-gspca-pac7311 \
	kernel-module-gspca-sn9c2028 \
	kernel-module-gspca-sn9c20x \
	kernel-module-gspca-sonixb \
	kernel-module-gspca-sonixj \
	kernel-module-gspca-spca500 \
	kernel-module-gspca-spca501 \
	kernel-module-gspca-spca505 \
	kernel-module-gspca-spca506 \
	kernel-module-gspca-spca508 \
	kernel-module-gspca-spca561 \
	kernel-module-gspca-sq905 \
	kernel-module-gspca-sq905c \
	kernel-module-gspca-stk014 \
	kernel-module-gspca-stv0680 \
	kernel-module-gspca-stv06xx \
	kernel-module-gspca-sunplus \
	kernel-module-gspca-t613 \
	kernel-module-gspca-tv8532 \
	kernel-module-gspca-vc032x \
	kernel-module-gspca-zc3xx \
	kernel-module-hdpvr \
	kernel-module-ibmcam \
	kernel-module-konicawc \
	kernel-module-msp3400 \
	kernel-module-mt9v011 \
	kernel-module-ov511 \
	kernel-module-ovcamchip \
	kernel-module-pvrusb2 \
	kernel-module-pwc \
	kernel-module-quickcam-messenger \
	kernel-module-s2255drv \
	kernel-module-saa7115 \
	kernel-module-se401 \
	kernel-module-sn9c102 \
	kernel-module-stkwebcam \
	kernel-module-stv680 \
	kernel-module-tuner \
	kernel-module-tveeprom \
	kernel-module-tvp5150 \
	kernel-module-ultracam \
	kernel-module-usbvideo \
	kernel-module-usbvision \
	kernel-module-uvcvideo \
	kernel-module-vicam \
	kernel-module-videobuf-core \
	kernel-module-videobuf-vmalloc \
	kernel-module-w9968cf \
	kernel-module-wm8775 \
	kernel-module-zc0301 \
	kernel-module-zr364xx \
	kernel-module-usbtest kernel-module-g-zero \
	linkjvm \ 
"
export IMAGE_BASENAME = "kipr"

