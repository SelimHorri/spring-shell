/*
 * Copyright 2011-2013 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.shell.commands;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.shell.core.CommandConstants.SYSTEM_PROPS_COMMAND;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.AbstractDefaultShellTest;
import org.springframework.shell.CommandResult;


/**
 * System properties command test.
 * 
 * @author David Winterfeldt
 */
public class SystemPropertiesCommandTest extends AbstractDefaultShellTest {
    
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testSystemPropertiesCommand() {
        String command = SYSTEM_PROPS_COMMAND;
        
        CommandResult cr = shell.exec(command);
        
        String outputText = cr.getOutputText();
        String result = (String) cr.getCommandOutput().get(command);

        // no real operation, just check that the command was processed in the shell
        assertNotNull("Output text for '" + command + "' command shouldn't be null.", result);
        assertTrue(outputText.contains(command));
        
        // verify expected properties are available in the results
        for (Object propertyKey : System.getProperties().keySet()) {
            String key = propertyKey + " = ";
            
            assertTrue("Expected '" + key + "' to be available in '" + command + "' output.", result.contains(key));
        }
        
        verifySuccess(cr);
    }
    
}