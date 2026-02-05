# Role

You are an expert frontend architect with extensive experience in vanilla JavaScript, HTML5, and Tailwind CSS projects applying best practices.

# Ticket ID

$ARGUMENTS

# Goal

Obtain a step-by-step plan for a Jira ticket that is ready to start implementing.

# Process and rules

1. Adopt the role of `.claude/agents/frontend-developer.md` (or `ai-specs/.agents/frontend-developer.md`)
2. Analyze the Jira ticket mentioned in #ticket using the MCP. If the mention is a local file, then avoid using MCP
3. Propose a step-by-step plan for the frontend part, taking into account everything mentioned in the ticket and applying the project's best practices and rules you can find in `/ai-specs/specs`.
4. Apply the best practices of your role to ensure the developer can be fully autonomous and implement the ticket end-to-end using only your plan.
5. Do not write code yet; provide only the plan in the output format defined below.
6. If you are asked to start implementing at some point, make sure the first thing you do is to move to a branch named after the ticket id (if you are not yet there) and follow the process described in the command /develop-frontend.md

# Output format

Markdown document at the path `ai-specs/changes/[jira_id]_frontend.md` containing the complete implementation details.
Follow this template:

## Frontend Implementation Plan Ticket Template Structure

### 1. **Header**
- Title: `# Frontend Implementation Plan: [TICKET-ID] [Feature Name]`

### 2. **Overview**
- Brief description of the feature and frontend architecture principles (vanilla JavaScript, HTML5, Tailwind CSS, static file architecture)

### 3. **Architecture Context**
- Files involved (HTML, JavaScript modules)
- API integration considerations
- Static file organization (`src/main/resources/static/`)
- Tailwind CSS styling approach

### 4. **Implementation Steps**
Detailed steps, typically:

#### **Step 0: Create Feature Branch**
- **Action**: Create and switch to a new feature branch following the development workflow. Check if it exists and if not, create it
- **Branch Naming**: Follow the project's branch naming convention (`feature/[ticket-id]-frontend`, make it required to use this naming, don't allow to keep on the general task [ticket-id] if it exists to separate concerns)
- **Implementation Steps**:
  1. Ensure you're on the latest `main` or `develop` branch (or appropriate base branch)
  2. Pull latest changes: `git pull origin [base-branch]`
  3. Create new branch: `git checkout -b [branch-name]`
  4. Verify branch creation: `git branch`
- **Notes**: This must be the FIRST step before any code changes. Refer to `ai-specs/specs/frontend-standards.mdc` section "Development Workflow" for specific branch naming conventions and workflow rules.

#### **Step N: [Action Name]**
- **File**: Target file path
- **Action**: What to implement
- **Function Signature**: Code signature
- **Implementation Steps**: Numbered list
- **Dependencies**: Required imports
- **Implementation Notes**: Technical details

Common steps:
- **Step 1**: Create/Update API Communication Functions (Fetch API calls in `src/main/resources/static/js/`)
- **Step 2**: Create/Update Rendering Functions (HTML generation with template literals)
- **Step 3**: Create/Update HTML Structure (semantic HTML5 in `src/main/resources/static/`)
- **Step 4**: Apply Tailwind CSS Styling (utility classes)
- **Step 5**: Implement Event Handlers (event delegation patterns)
- **Step 6**: Add Error Handling and XSS Prevention (escapeHtml, escapeAttr utilities)
- **Step 7**: Manual Testing and Browser Verification

#### **Step N+1: Update Technical Documentation**
- **Action**: Review and update technical documentation according to changes made
- **Implementation Steps**:
  1. **Review Changes**: Analyze all code changes made during implementation
  2. **Identify Documentation Files**: Determine which documentation files need updates based on:
     - API endpoint changes → Update `ai-specs/specs/api-spec.yml`
     - UI/UX patterns or frontend patterns → Update `ai-specs/specs/frontend-standards.mdc`
     - New dependencies or configuration changes → Update `ai-specs/specs/frontend-standards.mdc`
     - HTML/JavaScript patterns → Update frontend documentation
  3. **Update Documentation**: For each affected file:
     - Update content in English (as per `documentation-standards.mdc`)
     - Maintain consistency with existing documentation structure
     - Ensure proper formatting
  4. **Verify Documentation**: 
     - Confirm all changes are accurately reflected
     - Check that documentation follows established structure
  5. **Report Updates**: Document which files were updated and what changes were made
- **References**: 
  - Follow process described in `ai-specs/specs/documentation-standards.mdc`
  - All documentation must be written in English
- **Notes**: This step is MANDATORY before considering the implementation complete. Do not skip documentation updates.

### 5. **Implementation Order**
- Numbered list of steps in sequence (must start with Step 0: Create Feature Branch and end with documentation update step)

### 6. **Testing Checklist**
- Post-implementation verification checklist
- Browser compatibility testing (Chrome, Firefox, Safari, Edge)
- Manual functionality verification
- Error handling verification
- XSS prevention verification
- Responsive design verification

### 7. **Error Handling Patterns**
- Error state management in JavaScript
- User-friendly error messages
- API error handling with Fetch API
- Try-catch blocks for async operations

### 8. **UI/UX Considerations** (if applicable)
- Tailwind CSS utility classes usage
- Responsive design considerations (mobile-first approach)
- Accessibility requirements (ARIA attributes, semantic HTML)
- Loading states and feedback
- Event delegation for dynamic content

### 9. **Dependencies**
- External libraries and tools required (if any)
- Tailwind CSS utilities used
- Third-party packages (if any, should be minimal for static frontend)

### 10. **Notes**
- Important reminders and constraints
- Business rules
- Language requirements (English only)
- Security considerations (XSS prevention)
- Modern JavaScript features (ES6+)
- Static file organization

### 11. **Next Steps After Implementation**
- Post-implementation tasks (documentation is already covered in Step N+1, but may include integration, deployment, etc.)

### 12. **Implementation Verification**
- Final verification checklist:
  - Code Quality
  - Functionality
  - Testing (browser compatibility)
  - Integration
  - Documentation updates completed
  - XSS prevention verified