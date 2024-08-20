#ifndef CHATTERBOX_VIEW_H
#define CHATTERBOX_VIEW_H


#include <memory>
#include <functional>

#include "ftxui/component/screen_interactive.hpp"
#include "view_controller.h"

using namespace ftxui;

class view {
private:
    std::function<void(std::string)> on_send;
    std::function<void(std::string)> on_channel_change;
    std::function<std::vector<std::string>()> on_load_past_messages;

    struct state {
        std::vector<std::string> channel_list;
        int selected_channel;

        std::string message;

        int middle_split_size;
        int left_size;
    } state;

public:
    view(std::function<void(std::string)> on_send, std::function<void(std::string)> on_channel_change, std::function<std::vector<std::string>()> on_load_past_messages);
    void render();
};


#endif //CHATTERBOX_VIEW_H
