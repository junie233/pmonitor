# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.4.1)




# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add.library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.
add_library(
             native-lib
             SHARED

              src/client/linux/crash_generation/crash_generation_client.cc
              src/client/linux/handler/exception_handler.cc
              src/client/linux/handler/minidump_descriptor.cc
              src/client/linux/log/log.cc
              src/client/linux/microdump_writer/microdump_writer.cc
              src/client/linux/dump_writer_common/ucontext_reader.cc
              src/client/linux/dump_writer_common/seccomp_unwinder.cc
              src/client/linux/dump_writer_common/thread_info.cc
              src/client/linux/minidump_writer/linux_dumper.cc
              src/client/linux/minidump_writer/linux_ptrace_dumper.cc
              src/client/linux/minidump_writer/minidump_writer.cc
              src/client/minidump_file_writer.cc
              src/common/android/breakpad_getcontext.S
              src/common/convert_UTF.c
              src/common/md5.cc
              src/common/string_conversion.cc
              src/common/linux/elfutils.cc
              src/common/linux/file_id.cc
              src/common/linux/guid_creator.cc
              src/common/linux/linux_libc_support.cc
              src/common/linux/memory_mapped_file.cc
              src/common/linux/safe_readlink.cc
              nativecrashhandler.cpp )
include_directories(
                    src/common/linux/
                    src/common/
                    src/common/android/include
                    src/common/using_std_string.h
                    src/)

#add_subdirectory(
#                  src/main/jni/google-breakpad/)

find_library(
              log-lib
              log )

target_link_libraries(
                       native-lib
?                       ${log-lib} )