# ConfigInitializer

This app acts as a launcher application for a target application. You can use this app to initialize your target application with configuration variables such as API endpoints, killswitches, etc.
This makes it easier for your QA to validate your application against different config variables. Instead of you providing multiple build with different configurations, you can launch your application via ConfigInitializer to provide different config variables.

All you have to do is edit the `config.json` file contained inside the `assets` folder.

Please refer to [tutorial.md](https://github.com/thatrohit/ConfigInitializer/blob/master/tutorial.md) for a detailed description on how you can leverage the `config.json` to create your own UI elements.
