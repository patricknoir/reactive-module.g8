## Useful commands:

### Regenerate `context.conf` and `dependencies.conf`

In SBT shell:

```scala
bnkArchitecture/runMain com.moplay.context.Architecture
```

To get the `context.conf` propagated across the modules, just run in SBT shell:

```scala
serviceDependencies
```

### Bloop

Install and start the service:

```bash
brew install scalacenter/bloop/bloop
brew services start scalacenter/bloop/bloop
```

From now on, see https://scalacenter.github.io/bloop/docs/build-tools/sbt .

Export projects in the build:

```bash
sbt bloopInstall
```

Compile the `root` project:

```bash
bloop compile root
```

Run tests on `root` project:

```bash
bloop test root --propagate
```

### Environment secrets

Encrypt a secret

```bash
echo -n "<<secret-in-plain-text>>" | crypt encrypt aws --region <<target-aws-region>> --profile <<local-aws-profile-for-target-environment>> --kms <<kms-key-arn-or-alias-in-target-environment>> | base64
```

example: `echo -n "changeme" | crypt encrypt aws --region eu-west-2 --profile prod --kms alias/platform-eks1-eu-west-2-prod-addisonglobal-cloud | base64`

Decrypt a secret

```bash
echo -n "<<base64(encrypted_data)>>" | base64 -D | crypt decrypt aws --region <<target-aws-region>> --profile <<local-aws-profile-for-target-environment>>
```

example: `echo -n "base64-encrypted-data" | base64 -D | crypt decrypt aws --region eu-west-2 --profile moplay`

### Build and deploy local branch

Assuming we'll be deploying into *int5* environment (just need to change target environment if needed):

- We need to use non-shared ECR (as only Jenkins have write privileges into ECR ID `095522625957`). To achieve that, edit file `/architecture/int5.yaml` and update property `ecr_id` to ECR ID: `871201465636`.
- In order to build docker images from the local branch, just run:

Generate login command in ecr
```bash
\$(aws ecr get-login --no-include-email --profile <aws_profile: e.g:default> --region eu-west-2)
```

```bash
sbt branch-build-local
```

- Run the following scripts to: Render configuration, prepare deployment and deploy into the target environment:

```bash
amm render_configuration.sc int5
amm generateDeployment.sc
amm deploy.sc int5
```

- To run integration tests against that environment:

```bash
amm scripts/create_it_environments.sc int5
ENVIRONMENT=int5 sbt it:test
``
`
