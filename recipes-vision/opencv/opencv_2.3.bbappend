# The OpenCV .bb recipe is broken because of a bad link. This fixes it.
SRC_URI = "git://github.com/kipr/opencv.git"
SRCREV = "HEAD"

S = "${WORKDIR}/git"

PRINC = "3"
