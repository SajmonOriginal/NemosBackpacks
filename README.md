# NemosBackpacks (NeoForge 1.21.1 port)

NeoForge 1.21.1 port of [Nemo's Backpacks](https://github.com/NemoNotFound/NemosBackpacks)
by [Nemo (DevNemo)](https://github.com/NemoNotFound), with an added equip
system. Published with the original author's express written permission;
distribution is scoped to modpacks (see [LICENSE](LICENSE)).

The original mod (master branch, Fabric 26.1) is untouched. Only the
`version/1.21.1` branch is built and published from this repository.

## Install

1. Grab `nemos-backpacks-neoforge-<version>.jar` from
   [Releases](https://github.com/SajmonOriginal/NemosBackpacks/releases).
2. Drop it into the server's `mods/` directory (also client-side for the
   inventory UI).
3. Start the server. No config required.

## Build from source

```bash
./gradlew build
```

The jar lands in `build/libs/`.

## Publish

Every push to `version/1.21.1` triggers `.github/workflows/publish-maven.yml`,
which builds the jar, uploads it to `maven.sajmon.gg`, and attaches it to
a matching GitHub Release. Maven coordinates:

```
groupId    = cz.sajmonoriginal.nemos-backpacks-neoforge
artifactId = nemos-backpacks-neoforge
```

`MAVEN_USERNAME` and `MAVEN_TOKEN` repository secrets must be set for the
publish to succeed.

## Credits

- Original mod and assets: [Nemo (DevNemo)](https://github.com/NemoNotFound)
- NeoForge 1.21.1 port + equip system: [SajmonOriginal](https://github.com/SajmonOriginal)
