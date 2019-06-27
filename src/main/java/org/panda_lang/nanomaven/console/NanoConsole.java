/*
 * Copyright (c) 2017 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.nanomaven.console;

import org.panda_lang.nanomaven.NanoMaven;
import org.panda_lang.nanomaven.NanoMavenConstants;
import org.panda_lang.nanomaven.console.commands.*;

public class NanoConsole {

    private final NanoMaven nanoMaven;
    private final NanoConsoleThread consoleThread;
    private final String[] knownCommands = new String[] {
    	"reinstall-artifacts (rs) - Reinstall all artifacts",
    	"users - List all registered users",
    	"projects - List all added projects",
    	"add-user <username> <password> - Add user",
    	"add-project <repository>.<groupId>/<artifactId> - Add project extra data",
    	"add-member <repository>.<groupId>/<artifactId> <username> - Add user to the specified project"
    };

    public NanoConsole(NanoMaven nanoMaven) {
        this.nanoMaven = nanoMaven;
        this.consoleThread = new NanoConsoleThread(this);
    }

    @SuppressWarnings({ "unchecked "})
    public void hook() {
        consoleThread.start();
    }

    // TODO
    public void execute(String command) throws Exception {
        if (command.trim().isEmpty()) {
            return;
        }

        String[] elements = command.split(" ");

        if (command.equalsIgnoreCase("help")) {
            HelpCommand helpCommand = new HelpCommand();
            helpCommand.call(nanoMaven);
            return;
        }

        if (command.equalsIgnoreCase("reinstall-artifacts") || command.equalsIgnoreCase("rs")) {
            ReinstallArtifactsCommand reinstallArtifactsCommand = new ReinstallArtifactsCommand();
            reinstallArtifactsCommand.execute(nanoMaven);
            return;
        }

        if (command.equals("users")) {
            UsersCommand usersCommand = new UsersCommand();
            usersCommand.call(nanoMaven);
            return;
        }

        if (command.equals("projects")) {
            ProjectsCommand projectsCommand = new ProjectsCommand();
            projectsCommand.call(nanoMaven);
            return;
        }

        command = elements[0];

        if (command.equalsIgnoreCase("add-user")) {
        	if(elements.length < 3) {
        		NanoMaven.getLogger().warn("Incorrect usage: " + knownCommands[3]);
        		return;
        	}
            AddUserCommand addUserCommand = new AddUserCommand(elements[1], elements[2]);
            addUserCommand.call(nanoMaven);
            return;
        }

        if (command.equalsIgnoreCase("add-project")) {
        	if(elements.length < 2) {
        		NanoMaven.getLogger().warn("Incorrect usage: " + knownCommands[4]);
        		return;
        	}
            AddProjectCommand addProjectCommand = new AddProjectCommand(elements[1]);
            addProjectCommand.call(nanoMaven);
            return;
        }

        if (command.equalsIgnoreCase("add-member")) {
        	if(elements.length < 3) {
        		NanoMaven.getLogger().warn("Incorrect usage: " + knownCommands[5]);
        		return;
        	}
            AddMemberCommand addMemberCommand = new AddMemberCommand(elements[1], elements[2]);
            addMemberCommand.call(nanoMaven);
            return;
        }

        NanoMaven.getLogger().warn("Unknown command " + elements[0]);
        NanoMaven.getLogger().warn("NanoMaven " + NanoMavenConstants.VERSION + " Commands:");
        for(String cmd : knownCommands) {
        	NanoMaven.getLogger().warn("\t" + cmd);
        }
    }

}
