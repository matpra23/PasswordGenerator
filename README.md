# Password Generator

A Java-based password generator application with GUI and database storage capabilities.

## Features
- Generate secure random passwords with customizable options
- GUI interface for easy interaction
- SQLite database for password history
- Optional password saving with descriptions
- Password length validation (1-32 characters)
- Customizable character sets (numbers, letters, special characters)

## Changelog

### March 24, 2025
#### New Features
- Added GUI with checkboxes for password options
- Implemented SQLite database for password storage
- Added optional password saving functionality

### September 10, 2024
#### New Features
- Added exception handling for password length < 1
- Implemented lambda expression for password generation time calculation

#### Planned Features
- GUI implementation

### February 25, 2024
#### New Features
- Added custom password generation options
  - Option to exclude numbers
  - Option to exclude special characters

#### To Do
- Code optimization

### February 24, 2024
#### Fixes
- Fixed random password generation functionality

#### Planned Features
- Additional custom password generation options
  - Option to exclude numbers
  - Option to exclude special characters

## Technical Details
- Built with Java
- Uses Swing for GUI
- SQLite for database storage
- Maven for project management