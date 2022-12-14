package com.chaottic.lundraishot;

import org.lwjgl.opengl.GL;

import java.util.concurrent.TimeUnit;

import static org.lwjgl.glfw.GLFW.*;

public final class Main {

    private Main() {}

    public static void main(String[] args) {

        if (!glfwInit()) {
            throw new RuntimeException("Failed to initialize GLFW.");
        }

        var window = new Window();

        GL.createCapabilities();

        var previous = System.nanoTime();
        var delta = 0.0D;
        var fps = 1000000000 / 60;

        while (!window.shouldClose()) {
            var now = System.nanoTime();
            var elapsed = now - previous;

            previous = now;

            delta += (float) elapsed / fps;

            while (delta > 0.0D) {
                window.swapBuffers();

                glfwPollEvents();

                delta--;
            }

            var sleep = fps - elapsed;
            if (sleep > 0) {
                try {
                    //noinspection BusyWait
                    Thread.sleep(TimeUnit.NANOSECONDS.toMillis(sleep));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        window.destroy();

        glfwTerminate();
    }
}
