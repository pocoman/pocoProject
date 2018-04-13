export PATH=/usr/local/bin:$PATH
export PATH=/usr/local/sbin:$PATH
export PATH=/Users/p-kumu/Library/Python/2.7/bin:$PATH
export PATH=$HOME/.nodebrew/current/bin:$PATH
export CLASSPATH=$CLASSPATH:/Users/p-kumu/javacv-bin/javacv.jar:/Users/p-kumu/javacv-bin/javacpp.jar:.

alias ls='ls -Gh'
alias ll="ls -lGh"
alias la="ls -laGh"
alias wmp3="/bin/sh ~/Work/work_wmp3.sh"
alias wmp3_update="/bin/sh ~/Work/update_wmp3.sh"
alias wmp3_ctag="/bin/sh ~/Work/wmp3ctag.sh"

alias wmp3_r="/bin/sh ~/Work/work_wmp3_r.sh"
alias wmp3_r_update="/bin/sh ~/Work/update_wmp3_r.sh"
alias njcapi="/bin/sh ~/Work/work_api.sh"

alias wmp3_start="~/htdocs/auto_sftp/wmp3.sh"
alias api_start="~/htdocs/auto_sftp/api.sh"
alias work_stop="~/htdocs/auto_sftp/kill.sh"

alias ctags="`brew --prefix`/bin/ctags"
alias wmp3_release="/bin/sh ~/Work/eb_wmp3_test.sh"

alias pip="python -m pip";

export LSCOLORS=gxfxcxdxbxegedabagacad


source ~/.git-completion.bash

[[ -s "$HOME/.rvm/scripts/rvm" ]] && source "$HOME/.rvm/scripts/rvm" # Load RVM into a shell session *as a function*

if [ -f ~/.bashrc ] ; then
    . ~/.bashrc
fi

