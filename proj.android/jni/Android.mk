LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

$(call import-add-path,$(LOCAL_PATH)/../../cocos2d)
$(call import-add-path,$(LOCAL_PATH)/../../cocos2d/external)
$(call import-add-path,$(LOCAL_PATH)/../../cocos2d/cocos)

LOCAL_MODULE := cocos2dcpp_shared

LOCAL_MODULE_FILENAME := libcocos2dcpp

#LOCAL_SRC_FILES := hellocpp/main.cpp \
#                   ../../Classes/AppDelegate.cpp \
#                   ../../Classes/MainScene.cpp \
#                   ../../Classes/TitleScene.cpp \
#                   ../../Classes/AsobiKata.cpp \
#                   ../../Classes/IMobileCocos2dxModule.cpp \
#                   ../../Classes/NParticleSystemQuad.cpp \
#                   ../../Classes/ParticleSystemPool.cpp \
#                   ../../Classes/FstScene.cpp \
#                   ../../Classes/Sprite2.cpp \
#                    ../../Classes/Kiraru.cpp \
#                   ../../Classes/KiraruPool.cpp \
#                   ../../Classes/ImovileAd.cpp \
#                   ../../Classes/GameScene.cpp \
#                   ../../Classes/ModalLayer.cpp \
#                  ../../Classes/Item.cpp \
#                   ../../Classes/ItemPool.cpp \
#                   ../../Classes/SpritePool.cpp \
#                   ../../Classes/SelectScene.cpp \
#                   ../../Classes/ChrPrm.cpp \
#                   ../../Classes/Bike.cpp \
#                   ../../Classes/Slider.cpp \
#                   ../../Classes/TestScene.cpp \
#                   ../../Classes/Dot.cpp \
#                   ../../Classes/SelTag.cpp \
#                   ../../Classes/GameDeta.cpp
 
FILE_LIST := $(wildcard $(LOCAL_PATH)/../../Classes/*.cpp)

LOCAL_SRC_FILES := hellocpp/main.cpp
LOCAL_SRC_FILES += $(FILE_LIST:$(LOCAL_PATH)/%=%)

                   
LOCAL_C_INCLUDES := $(LOCAL_PATH)/../../Classes

LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static
LOCAL_WHOLE_STATIC_LIBRARIES += cocosdenshion_static

# LOCAL_WHOLE_STATIC_LIBRARIES += box2d_static
# LOCAL_WHOLE_STATIC_LIBRARIES += cocosbuilder_static
# LOCAL_WHOLE_STATIC_LIBRARIES += spine_static
# LOCAL_WHOLE_STATIC_LIBRARIES += cocostudio_static
# LOCAL_WHOLE_STATIC_LIBRARIES += cocos_network_static
# LOCAL_WHOLE_STATIC_LIBRARIES += cocos_extension_static


include $(BUILD_SHARED_LIBRARY)

$(call import-module,.)
$(call import-module,audio/android)

# $(call import-module,Box2D)
# $(call import-module,editor-support/cocosbuilder)
# $(call import-module,editor-support/spine)
# $(call import-module,editor-support/cocostudio)
# $(call import-module,network)
# $(call import-module,extensions)

APP_PLATFORM := android-9
