# Add RVM to PATH for scripting. Make sure this is the last PATH variable change.
export PATH="$PATH:$HOME/.rvm/bin"

# git settings
source /usr/local/Cellar/git/2.14.1/etc/bash_completion.d/git-prompt.sh
#source /usr/local/git/contrib/completion/git-completion.bash
export PATH="$(brew --prefix homebrew/php/php72)/bin:$PATH"

GIT_PS1_SHOWDIRTYSTATE=true
export PS1='\[\033[32m\]\u@\h\[\033[00m\]:\[\033[34m\]\w\[\033[31m\]$(__git_ps1)\[\033[00m\]\n\$ '
