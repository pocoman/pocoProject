# ---------------------------------------
# RVM PATH
# ---------------------------------------
export PATH="$PATH:$HOME/.rvm/bin"

# ---------------------------------------
# Git prompt (zsh)
# ---------------------------------------
if [ -f /usr/local/Cellar/git/2.14.1/etc/bash_completion.d/git-prompt.sh ]; then
    source /usr/local/Cellar/git/2.14.1/etc/bash_completion.d/git-prompt.sh
fi

# ---------------------------------------
# PHP / Homebrew PATH
# ---------------------------------------
export PATH="$(brew --prefix homebrew/php/php72)/bin:$PATH"

# ---------------------------------------
# Git PS1 settings
# ---------------------------------------
export GIT_PS1_SHOWDIRTYSTATE=true

# ---------------------------------------
# Prompt (zsh)
# ---------------------------------------
PROMPT='%F{green}%n@%m%f:%F{blue}%~%f%F{red}$(__git_ps1)%f
$ '