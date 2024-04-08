"sudo apt-get inatall vim vim-athena vim-common vim-gnome vim-gtk vim-gui-common vim-runtime vim-tiny
set wildmenu
set wildmode=longest:full
set tabstop=4
set shiftwidth=4
set autoindent
set expandtab
set softtabstop=0
set hlsearch
set ic
set ruler
set foldmarker=/*,*/
set foldmethod=manual
syntax on
nnoremap <Tab> gt
nnoremap <S-Tab> gT
nnoremap <Space>t :tabnew<CR>
nnoremap <Space>s :sort<CR>
nnoremap <C-P> :call PhpDocSingle()<CR>
nnoremap <Space>f <C-w><C-]><C-w>T
inoremap <C-P> :call PhpDocSingle()<CR>
inoremap <C-b> <ESC>bi
inoremap <C-w> <ESC>wi
inoremap <C-j> <DOWN>
inoremap <C-k> <UP>
inoremap <C-l> <RIGHT>
inoremap <C-h> <LEFT>
cnoremap <C-b> <LEFT>
cnoremap <C-f> <RIGHT>
cnoremap <C-a> <HOME>
vnoremap <silent> p :rv!<CR>p
vnoremap <silent> y y:wv<CR>
vnoremap <C-P> :call PhpDocRange()<CR>

set clipboard=unnamed,autoselect
set list
set listchars=tab:»-,trail:-,eol:↲,extends:»,precedes:«,nbsp:%
set ambiwidth=double
scriptencoding utf-8

set wildignorecase
set smartcase
highlight ZenkakuSpace cterm=underline ctermfg=gray
autocmd BufRead,BufNew,BufEnter * call matchadd("ZenkakuSpace", "　")
highlight HankakuSpace cterm=underline ctermfg=darkgrey
autocmd BufRead,BufNew,BufEnter * call matchadd("HankakuSpace", "^  *")
set list

highlight SpecialKey term=underline ctermfg=darkgrey guifg=darkgrey
au BufNewFile,BufRead *.jade setlocal filetype=jade
au BufNewFile,BufRead *.ts setlocal filetype=typescript
au BufNewFile,BufRead *.coffee setlocal filetype=coffee
set number
set undodir=~/.vimundo
set undofile

"neobundle
set nocompatible
"filetype plugin indent off
filetype plugin indent on

if has('vim_starting')
    set nocompatible
    set runtimepath+=~/.vim/bundle/neobundle.vim/
endif

call neobundle#begin(expand('~/.vim/bundle/'))
NeoBundleFetch 'Shougo/neobundle.vim'
NeoBundle 'PDV--phpDocumentor-for-Vim'
NeoBundle 'kchmck/vim-coffee-script'
NeoBundle 'flazz/vim-colorschemes'
NeoBundle 'tpope/vim-fugitive'
NeoBundle 'digitaltoad/vim-pug'
NeoBundle 'digitaltoad/vim-jade'
NeoBundle 'tpope/vim-pathogen'
NeoBundle 'vim-scripts/yanktmp.vim'
NeoBundle 'leafgarland/typescript-vim.git'
NeoBundle 'altercation/vim-colors-solarized'
NeoBundle 'croaker/mustang-vim'
NeoBundle 'nanotech/jellybeans.vim'
NeoBundle 'tomasr/molokai'
NeoBundle 'Shougo/unite.vim'
NeoBundle 'ujihisa/unite-colorscheme'
NeoBundle 'jacoborus/tender.vim'
filetype plugin indent on
NeoBundleCheck
call neobundle#end()

autocmd FileType php set tags=$HOME/.vim/tags/wmp3.tags
"autocmd FileType php set tags=$HOME/.vim/tags/njcrev.tags
"

augroup PHP
  autocmd!
  autocmd FileType php set makeprg=php\ -l\ %
  " php -lの構文チェックでエラーがなければ「No syntax errors」の一行だけ出力される
  autocmd BufWritePost *.php silent make | if len(getqflist()) != 1 | copen | else | cclose | endif
augroup END

command! -nargs=? Jq call s:Jq(<f-args>)
function! s:Jq(...)
    if 0 == a:0
        let l:arg = "."
    else
        let l:arg = a:1
    endif
    execute "%! jq \"" . l:arg . "\""
endfunction

command! -nargs=? Fr call s:Fr(<f-args>)
function! s:Fr(...)
    if 0 == a:0
        let l:arg = "2"
    else
        let l:arg = a:1
    endif
    execute l:arg.",$s/pick/squash"
endfunction

set conceallevel=0
let g:vim_json_syntax_conceal = 0
autocmd Filetype json setl conceallevel=0

" If you have vim >=8.0 or Neovim >= 0.1.5
if (has("termguicolors"))
    set termguicolors
endif

" For Neovim 0.1.3 and 0.1.4
let $NVIM_TUI_ENABLE_TRUE_COLOR=1
"
" Theme
syntax enable
colorscheme tender
