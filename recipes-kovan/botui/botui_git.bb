inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/meta-kipr.git \
           file://xorg.service \
           file://kovan-factory-test.service \
           file://factory.config"

SRCREV = "92daca803a73185162efa982772fdec38fe623eb"
LICENSE = "GPL"
PR = "r0"

S = "${WORKDIR}/git"

EXTRA_OECMAKE = "-DQT_LIBRARY_DIR=${OE_QMAKE_LIBDIR_QT} \
	-DQT_INSTALL_LIBS=${OE_QMAKE_LIBDIR_QT} \
	-DQT_INCLUDE_DIR=${OE_QMAKE_INCDIR_QT} \
	-DQT_HEADERS_DIR=${OE_QMAKE_INCDIR_QT} \
	-DQT_MOC_EXECUTABLE=${OE_QMAKE_MOC} \
	-DQT_UIC_EXECUTABLE=${OE_QMAKE_UIC} \
	-DQT_UIC3_EXECUTABLE=${OE_QMAKE_UIC3} \
	-DQT_RCC_EXECUTABLE=${OE_QMAKE_RCC} \
	-DQT_QMAKE_EXECUTABLE=${OE_QMAKE_QMAKE} \
	-DQT_QTCORE_INCLUDE_DIR=${OE_QMAKE_INCDIR_QT}/QtCore \
	-DQT_DBUSXML2CPP_EXECUTABLE=/usr/bin/qdbusxml2cpp \
	-DQT_DBUSCPP2XML_EXECUTABLE=/usr/bin/qdbuscpp2xml \
	-DQT_MKSPECS_DIR=${QMAKESPEC}/../ \
	"
OECMAKE_SOURCEPATH = "../botui"

do_install() {
	install -d ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/xorg.service ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/botui.service ${D}/lib/systemd/system

	install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
	ln -sf ../botui.service ${D}${base_libdir}/systemd/system/basic.target.wants/

	install -d ${D}/var/lib/connman
	install -m 0755 ${WORKDIR}/factory.config ${D}/var/lib/connman/

	install -d ${D}/usr/sbin
	install -m 0755 ${S}/kovan-test ${D}/usr/sbin/
}

FILES_${PN} = "${bindir} ${sbindir}"
FILES_${PN} += "${base_libdir}/systemd"
FILES_${PN} += "/var/lib/connman"