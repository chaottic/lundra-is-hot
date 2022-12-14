package com.chaottic.lundraishot;

import org.lwjgl.system.MemoryStack;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.memAddress;

public final class Window {
    private final long window;

    {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6);
        glfwWindowHint(GLFW_CONTEXT_RELEASE_BEHAVIOR, GLFW_RELEASE_BEHAVIOR_FLUSH);

        if ((window = glfwCreateWindow(854, 480, "Lundra Is Hot", NULL, NULL)) == NULL) {
            throw new RuntimeException("Failed to create a GLFW Window.");
        }

        var monitor = glfwGetPrimaryMonitor();
        var vidMode = glfwGetVideoMode(monitor);

        if (vidMode != null) {
            try (MemoryStack memoryStack = MemoryStack.stackPush()) {
                var buffer = memoryStack.callocInt(6);

                nglfwGetWindowSize(window, memAddress(buffer), memAddress(buffer) + 4);

                nglfwGetMonitorWorkarea(monitor, memAddress(buffer) + 8, memAddress(buffer) + 12, memAddress(buffer) + 16, memAddress(buffer) + 20);

                var width = buffer.get();
                var height = buffer.get();

                glfwSetWindowMonitor(window, NULL, (buffer.get(4) - width) / 2, (buffer.get(5) - height) / 2, width, height, vidMode.refreshRate());
            }
        }

        glfwMakeContextCurrent(window);
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    public void swapBuffers() {
        glfwSwapBuffers(window);
    }

    public void destroy() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
    }
}
