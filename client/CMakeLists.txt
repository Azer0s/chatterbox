cmake_minimum_required(VERSION 3.9)
project(chatterbox)

set(CMAKE_CXX_STANDARD 26)

add_executable(chatterbox main.cpp
        main.cpp
        view.cpp
        view.h
        view_controller.cpp
        view_controller.h)

include(FetchContent)
set(FETCHCONTENT_UPDATES_DISCONNECTED TRUE)

# ftxui
FetchContent_Declare(ftxui
        GIT_REPOSITORY https://github.com/ArthurSonzogni/ftxui
        GIT_TAG v5.0.0
)
FetchContent_MakeAvailable(ftxui)
target_link_libraries(chatterbox ftxui::screen ftxui::dom ftxui::component)
