#include <string>
#include <utility>

#include "ftxui/component/component.hpp"
#include "ftxui/component/screen_interactive.hpp"
#include "ftxui/dom/elements.hpp"
#include "ftxui/util/ref.hpp"

#include "view.h"

using namespace std::chrono_literals;
using namespace ftxui;

void view::render() {
    auto channel_select = Radiobox(&state.channel_list, &state.selected_channel);
    auto component_tree_left = Container::Vertical({
        channel_select,
    });

    auto left = Renderer(component_tree_left, [&] {
        return channel_select->Render() | borderEmpty | flex_grow;
    });

    auto input = Input(&state.message, "Message");

    input |= CatchEvent([&] (const Event& event) {
        if (event == Event::Return) {
            on_send(state.message);
            state.message.clear();
            return true;
        }
        return false;
    });

    auto middle_top = Renderer([&] {
        return text("Message: " + state.message) | center;
    });

    auto component_tree_middle_bottom = Container::Vertical({
        input,
    });

    auto middle_bottom = Renderer(component_tree_middle_bottom, [&] {
        return input->Render() | flex_grow;
    });

    auto middle = ResizableSplitBottom(middle_bottom, middle_top, &state.middle_split_size);

    auto container = ResizableSplitLeft(left, middle, &state.left_size);
    auto screen = ScreenInteractive::Fullscreen();

    container |= CatchEvent([&] (const Event& event) {
        if (event == Event::Special("channel_change")) {
            return true;
        }
        return false;
    });

    screen.Loop(container);
}

view::view(std::function<void(std::string)> on_send, std::function<void(std::string)> on_channel_change, std::function<std::vector<std::string>()> on_load_past_messages) {
    this->on_send = std::move(on_send);
    this->on_channel_change = std::move(on_channel_change);
    this->on_load_past_messages = std::move(on_load_past_messages);

    std::vector<std::string> channel_list = {
            "Use gcc",
            "Use clang",
            "Use emscripten",
            "Use tcc",
    };
    this->state.channel_list = channel_list;
    this->state.middle_split_size = 5;
    this->state.left_size = 40;
}
