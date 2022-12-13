package com.chaottic.lundraishot;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

public final class Main {

    private Main() {}

    public static void main(String[] args) {

        if (!glfwInit()) {
            throw new RuntimeException("Failed to initialize GLFW.");
        }

        var window = new Window();

        while (!window.shouldClose()) {

            window.swapBuffers();

            glfwPollEvents();
        }

        window.destroy();
    }
}
