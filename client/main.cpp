#include <memory>
#include <print>
#include <chrono>
#include <thread>

#include "ftxui/component/screen_interactive.hpp"

using namespace std::chrono_literals;

#include "view.h"

void client(const std::string& server, const std::string& port) {
    std::shared_ptr<view> v;

    v = std::make_shared<view>([&](const std::string& message) {
        (void) message;
    }, [&](const std::string& channel) {
        return;
    }, [&]() -> std::vector<std::string> {
        return {};
    });

    std::thread t([&] {
        std::this_thread::sleep_for(10s);

        auto event = ftxui::Event::Special("channel_change");
        ftxui::ScreenInteractive::Active()->PostEvent(event);
    });
    t.detach();

    v->render();
}

void printUsage(const std::string& program) {
    std::println("Usage: {} <server> <port>", program);
}

int main(int argc, char** argv) {
    if (argc < 3) {
        printUsage(argv[0]);
        return 1;
    }

    std::string server = argv[1];
    std::string port = argv[2];
    client(server, port);

    return 0;
}