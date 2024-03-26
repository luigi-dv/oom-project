# Overview
This user guide is designed to help you navigate through the application effortlessly, understand its features, and
leverage its capabilities

## Introduction
Welcome to Quackstagram, a software that allows students to share images with each other, similar to 
[Instagram](https://www.instagram.com).

### Target Audience
Whether you're a new user looking to get started with Quackstagram or a seasoned user seeking to explore advanced 
features, this guide will provide you with the necessary information to make the most out of the application.

By following the instructions and tips provided in this guide, you'll be able to:

- Efficiently navigate through the Quackstagram interface.
- Understand and utilize various features such as image sharing, commenting, and profile customization.
- Interact with fellow students and build connections within your educational community.
- Resolve common issues and troubleshoot any challenges you may encounter while using Quackstagram.

## System Requirements
Before installing and using Quackstagram, ensure that your system meets the following requirements:

### Hardware Requirements
- Processor: 1 GHz or faster processor
- RAM: 512 MB RAM or more
- Storage: 50 MB of free disk space
### Software Requirements
- Operating System: Any operating system supported by JDK 17, such as:
  - Windows 7/8/10
  - macOS 10.13+
  - Linux's distributions with GTK+ 3.22+
- Java Development Kit (JDK): JDK 17 or later installed on your system.
- Additional Dependencies: Minimum resolution of 1024x768 pixels with a 16-bit color depth.
  
These requirements ensure that your system can adequately support the execution of a Java Swing application built with JDK 17. Make sure to have the necessary JDK installed and configured on your system before proceeding with the installation and usage of the application.

## Installation
Here is the installation guide for clone Quackstagram GitLab repository:

<procedure title="Cloning from GitLab Repository" id="gitlab-repo-cloning">
    <step>
        <p>Open your terminal or command prompt.</p>
    </step>
    <step>
        <p>Navigate to the directory where you want to clone the repository.</p>
        <p>Use the <code>cd</code> command followed by the directory path.</p>
        <p>For example:</p>
        <code>cd /path/to/desired/directory</code>
    </step>
    <step>
        <p>Copy the repository URL from GitLab.</p>
        <p>It should look like: <code>https://gitlab.maastrichtuniversity.nl/bcs130_groupx_2024/quackstagram.git</code></p>
    </step>
    <step>
        <p>Run the following command to clone the repository:</p>
        <code>git clone repository_url</code>
        <p>Replace <code>repository_url</code> with the URL you copied.</p>
    </step>
    <step>
        <p>Enter your GitLab credentials if prompted.</p>
        <p>This step is required if the repository is private and requires authentication.</p>
        <p>You will need to create a <a href="https://docs.gitlab.com/ee/user/profile/personal_access_tokens.html">PAT (Personal Access Token)</a> and use it as a password.</p>
    </step>
    <step>
        <p>Wait for the cloning process to complete.</p>
        <p>Once finished, you should see a message indicating the successful cloning of the repository.</p>
    </step>
    <step>
        <p>Navigate into the cloned repository directory.</p>
        <p>Use the <code>cd</code> command followed by the repository name.</p>
        <p>For example:</p>
        <code>cd quackstagram</code>
    </step>
    <step>
        <p>Now you have successfully cloned the GitLab repository to your local machine.</p>
        <p>You can start working on the project or exploring its contents.</p>
    </step>
</procedure>

## Getting Started

To begin using the Quackstagram application, follow these simple steps:

<tabs>
    <tab title="Compile the Source Code">
        If the application's source code is not pre-compiled, you'll need to compile it using the following command:
        <code-block lang="bash"></code-block>
    </tab>
    <tab title="Semantic markup">
        <code-block lang="xml">
            <![CDATA[<img src="new_topic_options.png" alt="Alt text" width="450px"/>]]></code-block>
    </tab>
</tabs>

### User Interface Overview
Apart from injecting entire XML elements, you can use attributes to configure the behavior of certain elements.
For example, you can collapse a chapter that contains non-essential information:

#### Supplementary info {collapsible="true"}
Content under a collapsible header will be collapsed by default,
but you can modify the behavior by adding the following attribute:
`default-state="expanded"`

### Functionality Guide
If you need to extend an element with more functions, you can convert selected content from Markdown to semantic markup.
For example, if you want to merge cells in a table, it's much easier to convert it to XML than do this in Markdown.
Position the caret anywhere in the table and press <shortcut>Alt+Enter</shortcut>:

<img src="convert_table_to_xml.png" alt="Convert table to XML" width="706" border-effect="line"/>

## Feedback and support
Please report any issues, usability improvements, or feature requests to our
<a href="https://youtrack.jetbrains.com/newIssue?project=WRS">YouTrack project</a>
(you will need to register).

You are welcome to join our
<a href="https://jb.gg/WRS_Slack">public Slack workspace</a>.
Before you do, please read our [Code of conduct](https://plugins.jetbrains.com/plugin/20158-writerside/docs/writerside-code-of-conduct.html).
We assume that you’ve read and acknowledged it before joining.

You can also always email us at [writerside@jetbrains.com](mailto:writerside@jetbrains.com).