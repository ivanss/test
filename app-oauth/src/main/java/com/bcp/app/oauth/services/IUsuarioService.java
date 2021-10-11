package com.bcp.app.oauth.services;

import com.bcp.app.user.commons.models.entity.Usuario;

public interface IUsuarioService {

    public Usuario findByUsername(String username);

    public Usuario update(Usuario usuario, Long id);

}
