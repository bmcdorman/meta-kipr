# The OpenCV .bb recipe is broken because of a bad link. This fixes it.
SRC_URI = "git://github.com/kipr/opencv.git"
SRCREV = "HEAD"

EXTRA_OECMAKE="-DBUILD_PERF_TESTS=OFF -DBUILD_TESTS=OFF -DWITH_CUDA=OFF -DWITH_CUFFT=OFF -DWITH_OPENCL=OFF -DWITH_OPENCLAMDFFT=OFF -DWITH_OPENCLAMDBLAS=OFF"

S = "${WORKDIR}/git"

PRINC = "10"
