# The OpenCV .bb recipe is broken because of a bad link. This fixes it.
SRC_URI = "git://code.opencv.org/opencv.git"
SRCREV = "2.4.3"

S = "${WORKDIR}/git"

PRINC = "1"
