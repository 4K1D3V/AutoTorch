# AutoTorch Plugin

**Author**: KiteGG  
**Version**: 1.0

## Description
The AutoTorch plugin automatically places torches when players move into dark areas. It enhances the gameplay experience by ensuring visibility in caves and other low-light environments.

---

## Features
- Automatically places torches when the light level falls below a configurable threshold.
- Configurable cooldown to prevent excessive torch placement.
- Permissions for toggling and using the plugin:
    - `autotorch.use`
    - `autotorch.toggle`
    - `autotorch.admin.reload`
- Commands:
    - `/autotorch reload`: Reloads the plugin's configuration.
    - `/autotorch toggle`: Toggles the AutoTorch feature for the player.
- Fully customizable via `config.yml`.

---

## Installation
1. Download the plugin JAR file.
2. Place the JAR file in your server's `plugins` folder.
3. Start or restart your server.
4. Customize the configuration in `config.yml` if needed.

---

## Configuration
The plugin's configuration file, `config.yml`, is generated automatically upon first run. Here are the available options:

```yaml
# Configuration for AutoTorch Plugin
enabled: true
light-level-threshold: 7
placement-cooldown-seconds: 5
```

---

## Permissions
| Permission              | Description                                       | Default |
|-------------------------|---------------------------------------------------|---------|
| `autotorch.use`         | Allows players to use the AutoTorch feature.      | `true`  |
| `autotorch.toggle`      | Allows players to toggle AutoTorch on/off.        | `true`  |
| `autotorch.admin.reload`| Allows admins to reload the plugin configuration. | `op`    |

---

## Commands
- **`/autotorch reload`**: Reloads the plugin's configuration. Requires the permission `autotorch.admin.reload`.
- **`/autotorch toggle`**: Toggles the AutoTorch feature for the player. Requires the permission `autotorch.toggle`.

---

## Build Instructions
To build the plugin from source:
1. Clone the repository.
2. Ensure you have Maven or Gradle installed.
3. Run the build command:
    - For Maven: `mvn clean package`
    - For Gradle: `gradle build`
4. The compiled JAR will be located in the `target` or `build/libs` directory.

---

## License
This plugin is open-source and can be modified or redistributed under the terms of the MIT License.

## Credits 

- [KiteGG](https://github.com/4K1D3V)