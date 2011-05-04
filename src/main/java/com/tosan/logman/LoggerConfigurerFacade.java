/**
 *
 */
package com.tosan.logman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import ch.qos.logback.classic.jmx.JMXConfiguratorMBean;
import ch.qos.logback.core.joran.spi.JoranException;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.tosan.logman.exception.InvalidCommandArgumentException;

/**
 * @author mjafari
 *
 */
public class LoggerConfigurerFacade {
	private final String CONNECTOR_ADDRESS_PROPERTY = "com.sun.management.jmxremote.localConnectorAddress";
	private final String JMX_CONFIGURER_OBJECT_NAME = "ch.qos.logback.classic:Name=default,Type=ch.qos.logback.classic.jmx.JMXConfigurator";
	private Integer pid;
	private JMXConfiguratorMBean jmxConfigurator;

	public LoggerConfigurerFacade() {
		super();
	}

	public LoggerConfigurerFacade(int pid) {
		super();
		this.pid = pid;
	}

	public void connect() throws Exception {
		if (this.pid == null)
			throw new IllegalStateException("pid isn't set.");
		connect(this.pid);
	}

	public List<String> getJavaProcessDescriptions ()  {
		List<String> processDescList = new LinkedList<String>();
		for (VirtualMachineDescriptor vmd : VirtualMachine.list()) {
			processDescList.add(vmd.id() + ": " + vmd.displayName());
		}
		return processDescList;
	}

	public void connect(int pid) throws IOException, MalformedObjectNameException, InvalidCommandArgumentException {
		VirtualMachine vm = null;
		for (VirtualMachineDescriptor vmd : VirtualMachine.list()) {
			if (vmd.id().equals(String.valueOf(pid))) {
				System.out.println("selected:" + vmd);
				try {
					vm = VirtualMachine.attach(vmd);
				} catch (AttachNotSupportedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		if (vm == null) {
			throw new InvalidCommandArgumentException("pid not found.");
		}

		Properties props;
		String connectorAddress = null;
		try {
			props = vm.getAgentProperties();
			connectorAddress = props.getProperty(CONNECTOR_ADDRESS_PROPERTY);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (connectorAddress == null) {
			try {
				props = vm.getSystemProperties();
				String home = props.getProperty("java.home");
				String agent = home + File.separator + "lib" + File.separator
						+ "management-agent.jar";
				vm.loadAgent(agent);
				props = vm.getAgentProperties();
				connectorAddress = props
						.getProperty(CONNECTOR_ADDRESS_PROPERTY);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (AgentLoadException e) {
				e.printStackTrace();
			} catch (AgentInitializationException e) {
				e.printStackTrace();
			}
		}
		JMXServiceURL url = new JMXServiceURL(connectorAddress);
		JMXConnector jmxConnector = JMXConnectorFactory.connect(url);
		MBeanServerConnection mbsc = jmxConnector.getMBeanServerConnection();
		ObjectName logbackConfigurerObjectName = new ObjectName(
				JMX_CONFIGURER_OBJECT_NAME);
		jmxConfigurator = JMX.newMBeanProxy(mbsc, logbackConfigurerObjectName,
				JMXConfiguratorMBean.class);
	}

	public void reload() throws JoranException {
		if(jmxConfigurator == null)
			throw new IllegalStateException("connect is required before reload.");
		jmxConfigurator.reloadDefaultConfiguration();
	}

	public void load(String configFilePath) throws JoranException,
			FileNotFoundException {
		if(jmxConfigurator == null)
			throw new IllegalStateException("connect is required before load.");
		jmxConfigurator.reloadByFileName(configFilePath);

	}

	public void setLevel(String loggerName, String strLogLevel) {
		if(jmxConfigurator == null)
			throw new IllegalStateException("connect is required before setLevel.");
		jmxConfigurator.setLoggerLevel(loggerName, strLogLevel);
	}

}
