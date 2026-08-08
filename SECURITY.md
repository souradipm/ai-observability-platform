# Security Policy

## Supported Versions

Security fixes are currently provided for the latest version of the project available on the `main` branch.

| Version | Supported |
|---------|-----------|
| main    | Yes       |

## Reporting a Vulnerability

If you discover a security vulnerability in this project, please do not create a public GitHub issue.

Instead, report the vulnerability privately through GitHub's security reporting mechanism.

Please include:

- Description of the vulnerability
- Steps to reproduce
- Potential impact
- Affected component or endpoint
- Suggested mitigation, if known

## Security Expectations

This project is intended to demonstrate production-grade engineering practices.

Security considerations include:

- Authentication and authorization
- Secret management
- API security
- Input validation
- Dependency security
- Secure logging
- Protection of sensitive information
- Secure configuration management
- Least-privilege access
- Auditability

## Secrets

Never commit the following to the repository:

- API keys
- Passwords
- Access tokens
- Private keys
- Certificates containing private material
- Database credentials
- Cloud credentials
- Production configuration containing secrets

Local secrets should be supplied through environment variables or an appropriate secret-management mechanism.

## Responsible Disclosure

Please allow reasonable time for a vulnerability to be investigated and addressed before publicly disclosing it.

Thank you for helping improve the security of this project.